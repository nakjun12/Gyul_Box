import { atom } from "recoil";

type User = {
  name: string;
  age: number;
};

export const userAtom = atom<User | null>({
  key: "userAtom",
  default: null,
});
