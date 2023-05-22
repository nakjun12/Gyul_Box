import * as StompJs from "@stomp/stompjs";
import { useEffect, useRef, useState } from "react";
import { useRecoilValue } from "recoil";

import CurrentRoomIdState from "../../../lib/recoil/currentRoom";
import Profile from "../../../lib/recoil/profile";
type Props = {};

interface Message {
  senderId: number;
  receiverId: number;
  messageContent: string;
  messageRoomId: number;
}

interface MessageRoom {
  // 정의되어 있는 필드 타입에 맞게 수정하세요
  // 예시: messages: Message[]
  messages: Message[];
}

const useLiveChat = () => {
  const [text, setText] = useState("");
  const [messageRoom, setMessageRoom] = useState({});
  const CurrentRoomId = useRecoilValue(CurrentRoomIdState);
  const { profileId } = useRecoilValue(Profile);
  const [receiverId, setReceiverId] = useState(0);
  const client = useRef<StompJs.Client | null>(null); //

  const URL = "https://api-tutordiff.site/stomp/content";

  useEffect(() => {
    const setRoom = async () => {
      await initialChatSetting();
      connect();
    };
    setRoom();
    return () => disconnect();
  }, [CurrentRoomId]);

  const initialChatSetting = async () => {
    if (CurrentRoomId !== 0 && CurrentRoomId !== undefined) {
      try {
        const response = await fetch(
          `/messages/rooms/${profileId}/${CurrentRoomId}`
        );
        const { data } = await response.json();
        if (profileId === data.tuteeId) {
          setReceiverId(data.tutorId);
        } else {
          setReceiverId(data.tuteeId);
        }
        setMessageRoom(data);
      } catch (err) {
        console.log(err);
      }
    }
  };

  const connect = () => {
    client.current = new StompJs.Client({
      webSocketFactory: () => URL,
      connectHeaders: {
        Authorization: sessionStorage.getItem("authorization") || "",
      },
      debug: () => {},
      reconnectDelay: 3000,
      heartbeatIncoming: 2000,
      heartbeatOutgoing: 2000,
      onConnect: () => {
        subscribe();
      },
      onStompError: (frame) => {
        console.error(frame);
      },
    });

    client.current.activate();
  };

  const disconnect = () => {
    if (client.current) {
      client.current.deactivate();
    }
  };

  const subscribe = () => {
    if (client.current) {
      client.current.subscribe(`/sub/room/${CurrentRoomId}`, (res) => {
        setMessageRoom((prev) => ({
          ...prev,
          // messages: [...prev.messages, JSON.parse(res.body)],
        }));
      });
    }
  };

  const publish = (type = "") => {
    if (client.current && client.current.connected) {
      let sendText = "";
      switch (type) {
        case "":
          sendText = text;
          break;
        case "matchingConfirm":
          sendText = "MAT_CHING_CON_FIRM";
          break;
        case "matchingCancel":
          sendText = "MAT_CHING_CAN_CEL";
          break;
        default:
          break;
      }

      const message: Message = {
        senderId: profileId,
        receiverId: receiverId,
        messageContent: sendText,
        messageRoomId: CurrentRoomId,
      };

      client.current.publish({
        destination: `/pub/chats/${CurrentRoomId}`,
        body: JSON.stringify(message),
      });
      setText("");
    }
  };

  return {
    disconnect,
    publish,
    messageRoom,
    setMessageRoom,
    text,
    setText,
  };
};

export default useLiveChat;
