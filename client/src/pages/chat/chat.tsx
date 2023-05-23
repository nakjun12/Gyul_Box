import { useEffect } from "react";
import useLiveChat from "../../component/organisms/message/message";
const MyComponent = () => {
  const { publish, disconnect, messageRoom, setMessageRoom, text, setText } =
    useLiveChat();

  console.log(messageRoom, "messageRoom");
  useEffect(() => {
    // const subscription = subscribeToDestination(
    //   "https://0e69-39-120-170-15.ngrok-free.app"
    // );

    // Unsubscribe when component unmounts
    return () => {};
  }, []);

  const handleClick = () => {
    // sendMessage("YOUR_DESTINATION", "Hello, world!");
  };

  return (
    <div>
      <button onClick={handleClick}>Send Message</button>
    </div>
  );
};

export default MyComponent;
