import { useState } from "react";
import { BiMap } from "react-icons/bi";
import LoginButton from "../../component/atoms/loginButton/LoginButton";
import Pagination from "../../component/molecules/pagination/PagiNation";
import SearchBar from "../../component/molecules/searchBar/SearchBar";
import styles from "../../component/molecules/tabPanel/MypagePanel.module.scss";
import InterestModal from "../../component/organisms/modal/InterestModal";
import { codeToAddress } from "../../utils/Dummy";
type Props = { id: string; area: string; data: any };

export default function Index({ id, area, data }: Props) {
  console.log(codeToAddress(area), id, "이거");
  console.log(data, "data");
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
              양도 게시판
            </label>
            <div className="list_header_right">
              <InterestModal>
                <div className={styles.cardbox}>
                  <div className={styles.text}>지역선택</div>
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
        <Pagination currentPage={1} totalPages={12} area={""} />
        <div className="header_search">
          <SearchBar isData />
        </div>
      </main>
    </>
  );
}
