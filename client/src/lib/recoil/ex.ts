import { atom } from "recoil";

type User = {
  id: Number;
  name: string;
  email: string;
};

export const userAtom = atom<User | null>({
  key: "userAtom",
  default: null,
});
