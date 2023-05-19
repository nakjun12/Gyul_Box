import Reload from "../../molecules/reload/Reload";
import SimpleHouse from "../../molecules/simpleHouse/SimpleHouse";
type Props = {
  geo: [string, number];
};

export default function MapFooter({ geo }: Props) {
  return (
    <>
      <Reload geo={geo} />
      <SimpleHouse />
    </>
  );
}
