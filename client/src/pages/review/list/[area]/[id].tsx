import { GetStaticPaths, GetStaticProps } from "next";
import { Fragment, useState } from "react";
import { BiMap } from "react-icons/bi";
import LoginButton from "../../../../component/atoms/loginButton/LoginButton";
import BestPost from "../../../../component/molecules/bestPosts/BestPost";
import Pagination from "../../../../component/molecules/pagination/PagiNation";
import SearchBar from "../../../../component/molecules/searchBar/SearchBar";
import styles from "../../../../component/molecules/tabPanel/MypagePanel.module.scss";
import InterestModal from "../../../../component/organisms/modal/InterestModal";
import { BACK_URL } from "../../../../utils/ConfigHelper";
import { codeToAddress } from "../../../../utils/Dummy";
import type {
  Review_list,
  Review_list_data,
} from "../../../../utils/types/types";

type Props = { id: string; area: string; data: Review_list };

export default function Index({ id, area, data }: Props) {
  console.log(codeToAddress(area), id, "이거");

  console.log(data, "data");
  const [isSort, setIsSort] = useState(true);
  const [isData, setIsDate] = useState<boolean>(true);
  const address = codeToAddress(area);
  const handler = () => {
    setIsSort(!isSort);
  };
  const datas: Review_list_data[] = data ? data.data : [];

  return (
    <>
      <main className="review_editor_container">
        <div className="editor_wrapper">
          <div className="list_header">
            <label htmlFor="title" className="title_lable">
              {address || "노형동"} 리뷰
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
          {datas ? (
            datas.map((data: Review_list_data, i) => (
              <Fragment key={i}>
                <BestPost data={data} />
              </Fragment>
            ))
          ) : (
            <div>해당 지역 리뷰가 존재하지 않습니다</div>
          )}
        </div>
        <Pagination
          currentPage={Number(id)}
          totalPages={Number(data.pageInfo.totalPages)}
          area={area}
        />
        <div className="header_search">
          <SearchBar isData />
        </div>
      </main>
    </>
  );
}

export const getStaticPaths: GetStaticPaths = async () => {
  return {
    paths: [],
    fallback: "blocking",
  };
};

export const getStaticProps: GetStaticProps = async ({ params }) => {
  try {
    console.log(params, "params");
    const { area, id } = params as { area: string; id: string };

    const res = await fetch(
      `${BACK_URL}/areas/5011010100/reviews?page=1&size=4`
    );
    console.log(res, "res");
    const data = await res.json();

    if (res.status === 404) {
      return { notFound: true, props: { id, area } };
    }

    return { props: { data, id, area } };
  } catch (error) {
    console.error("An error occurred during the fetch request:", error);
    return { props: {} };
  }
};
