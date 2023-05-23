import * as StompJs from "@stomp/stompjs";
import { useEffect, useRef, useState } from "react";
import { useRecoilValue } from "recoil";
import SockJS from "sockjs-client";
import CurrentRoomIdState from "../../../lib/recoil/currentRoom";
import Profile from "../../../lib/recoil/profile";
import { WEBSOCKET_URL } from "../../../utils/ConfigHelper";
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

  const URL = WEBSOCKET_URL;
  console.log(URL);
  console.log(URL, WEBSOCKET_URL);
  useEffect(() => {
    const setRoom = async () => {
      // await initialChatSetting();
      connect();
    };
    setRoom();
    return () => disconnect();
  }, []);

  // const initialChatSetting = async () => {
  //   if (CurrentRoomId !== 0 && CurrentRoomId !== undefined) {
  //     try {
  //       const response = await fetch(
  //         `/messages/rooms/${profileId}/${CurrentRoomId}`
  //       );
  //       const { data } = await response.json();
  //       if (profileId === data.tuteeId) {
  //         setReceiverId(data.tutorId);
  //       } else {
  //         setReceiverId(data.tuteeId);
  //       }
  //       setMessageRoom(data);
  //     } catch (err) {
  //       console.log(err);
  //     }
  //   }
  // };

  const connect = () => {
    client.current = new StompJs.Client({
      webSocketFactory: () => {
        const socket = new SockJS(URL);

        // WebSocket 연결이 성공하면 헤더를 설정
        // socket.onopen = () => {
        //   socket.send("ngrok-skip-browser-warning: true");
        // };

        return socket;
      },
      connectHeaders: {
        ngrokskipbroswer: "true",
      },
      debug: () => {
        console.log("하이");
      },
      reconnectDelay: 3000,
      heartbeatIncoming: 2000,
      heartbeatOutgoing: 2000,
      onConnect: () => {
        console.log("연결");
        subscribe();
      },
      onStompError: (frame) => {
        console.error(frame);
        console.log("실패했다");
      },
    });
    // Set the binaryType here
    client.current.activate();
  };

  const disconnect = () => {
    if (client.current) {
      client.current.deactivate();
    }
  };

  const subscribe = () => {
    console.log("구독");
    if (client.current) {
      client.current.subscribe(`/3`, (res) => {
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
