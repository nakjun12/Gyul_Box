import Modal from "@/component/templates/modal/Modal";

type Props = {};

export default function index({}: Props) {
  return (
    <>
      <div>index</div>
      <Modal isOpen={true}>하이</Modal>
      그치
    </>
  );
}
