import { Client, StompHeaders } from "@stomp/stompjs";

const stompClient = new Client({
  brokerURL: "https://0e69-39-120-170-15.ngrok-free.app", // Replace with your broker URL
  // Other configuration options...
});

stompClient.activate();

const subscribeToDestination = (destination: string) => {
  const headers: StompHeaders = {
    // Optional headers
  };

  const subscription = stompClient.subscribe(
    destination,
    (message) => {
      // Handle received message
      console.log("Received message:", message.body);
    },
    headers
  );

  return subscription;
};

const sendMessage = (
  destination: string,
  message: string,
  headers: StompHeaders = {}
) => {
  stompClient.publish({
    destination,
    body: message,
    headers,
  });
};

export { stompClient, subscribeToDestination, sendMessage };
