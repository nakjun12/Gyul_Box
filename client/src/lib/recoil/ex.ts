import { atom } from "recoil";
import type { User } from "../../types/typelist";

export const userAtom = atom<User | null>({
  key: "userAtom",
  default: null,
});
