import SimpleHouse from "../../molecules/simpleHouse/SimpleHouse";

import Reload from "../../molecules/reload/Reload";
type Props = {};

export default function MapFooter({}: Props) {
  return (
    <>
      <Reload />
      <SimpleHouse />
    </>
  );
}
