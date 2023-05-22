import { useEffect } from "react";
import { sendMessage, subscribeToDestination } from "../../lib/sample";
const MyComponent = () => {
  useEffect(() => {
    const subscription = subscribeToDestination(
      "https://0e69-39-120-170-15.ngrok-free.app"
    );

    // Unsubscribe when component unmounts
    return () => {
      subscription.unsubscribe();
    };
  }, []);

  const handleClick = () => {
    sendMessage("YOUR_DESTINATION", "Hello, world!");
  };

  return (
    <div>
      <button onClick={handleClick}>Send Message</button>
    </div>
  );
};

export default MyComponent;
