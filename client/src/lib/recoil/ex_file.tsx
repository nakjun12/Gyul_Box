import { useRecoilState } from "recoil";
import type { User } from "../../types/typelist";
import { userAtom } from "./ex";

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
