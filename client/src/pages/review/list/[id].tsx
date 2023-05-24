import { useState } from "react";
import { BiMap } from "react-icons/bi";
import LoginButton from "../../../component/atoms/loginButton/LoginButton";
import Pagination from "../../../component/molecules/pagination/PagiNation";
import SearchBar from "../../../component/molecules/searchBar/SearchBar";
import InterestModal from "../../../component/organisms/modal/InterestModal";
import styles from "./../../../component/molecules/tabPanel/MypagePanel.module.scss";
type Props = {};

export default function Index({}: Props) {
  const [isSort, setIsSort] = useState(true);
  const [isData, setIsDate] = useState<boolean>(true);

  const handler = () => {
    setIsSort(!isSort);
  };

  return (
    <>
      <main className="review_editor_container">
        <div className="editor_wrapper">
          <div className="list_header">
            <label htmlFor="title" className="title_lable">
              노형동 리뷰
            </label>
            <div className="list_header_right">
              <InterestModal>
                <div className={styles.cardbox}>
                  <div className={styles.text}>관심지역</div>
                  <BiMap size={16} />
                </div>
              </InterestModal>

              {isSort ? (
                <LoginButton onClick={handler}>인기순</LoginButton>
              ) : (
                <LoginButton onClick={handler}>최신순</LoginButton>
              )}
            </div>
          </div>
          {/* <BestPost />
          <BestPost />
          <BestPost />
          <BestPost /> */}
        </div>
        <Pagination currentPage={1} totalPages={12} />
        <div className="header_search">
          <SearchBar isData />
        </div>
      </main>
    </>
  );
}
