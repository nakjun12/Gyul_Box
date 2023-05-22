import type { Map_circle } from "../../../utils/types/types";
import Reload from "../../molecules/reload/Reload";
import SimpleHouse from "../../molecules/simpleHouse/SimpleHouse";
type Props = {
  geo: [string, number];
  setAddress: React.Dispatch<React.SetStateAction<Map_circle>>;
  serverOn: boolean;
};

export default function MapFooter({ geo, setAddress, serverOn }: Props) {
  return (
    <>
      <Reload geo={geo} setAddress={setAddress} serverOn={serverOn} />
      <SimpleHouse />
    </>
  );
}
