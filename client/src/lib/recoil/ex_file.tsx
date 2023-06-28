import { useRecoilState } from "recoil";

import { userAtom } from "./ex";

export interface User {
  id: Number;
  name: string;
  email: string;
}
export default function MyComponent() {
  const [user, setUser] = useRecoilState<User | null>(userAtom);

  const handleLogin = () => {
    setUser({
      id: 1,
      name: "John",
      email: "john@example.com",
    });
  };

  return (
    <div>
      {user ? (
        <div>
          <div>{user.name}</div>
          <div>{user.email}</div>
        </div>
      ) : (
        <button onClick={handleLogin}>Login</button>
      )}
    </div>
  );
}
