import { AtomEffect, atom } from "recoil";

// key: 저장소에 저장되는 key 값
// setSelf: 연결된 atom 의 값을 초기화 해주는 함수
// onSet: 해당하는 atom 의 값이 변경이 되었을 때 실행되는 함수

const localStorageEffect: <T>(key: string) => AtomEffect<T> =
  (key: string) =>
  ({ setSelf, onSet }) => {
    if (typeof window !== "undefined") {
      const savedValue = localStorage.getItem(key);
      if (savedValue != null) {
        setSelf(JSON.parse(savedValue));
      }

      onSet((newValue, _, isReset) => {
        isReset
          ? localStorage.removeItem(key)
          : localStorage.setItem(key, JSON.stringify(newValue));
      });
    }
  };

const Profile = atom({
  key: "Profile",
  default: {
    isLogin: false,
    userStatus: "",
    profileId: 0,
    name: "",
    url: "",
    alarmCheck: false,
  },
  effects: [localStorageEffect("current_user")],
});

export default Profile;
