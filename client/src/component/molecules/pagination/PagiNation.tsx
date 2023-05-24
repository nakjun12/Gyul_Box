import { GetStaticPaths, GetStaticProps } from "next";
import Link from "next/link";
import { useState } from "react";
import { BACK_URL } from "../../../utils/ConfigHelper";
import styles from "./PagiNation.module.scss";
interface PaginationProps {
  currentPage: number;
  totalPages: number;
}

const Pagination: React.FC<PaginationProps> = ({ currentPage, totalPages }) => {
  const [isPage, setIsPage] = useState(currentPage);
  const prevPage = isPage > 1 ? isPage - 1 : 0;
  const nextPage = isPage < totalPages ? isPage + 1 : 0;
  console.log(nextPage, "히히히히ㅣ히ㅣ");
  const pageRange = 2;
  let startPage = isPage - pageRange;
  let endPage = isPage + pageRange;
  console.log(startPage, endPage);
  //다시 확인할것
  if (startPage < 1) {
    endPage += 1 - startPage;
    startPage = 1;
  }

  if (endPage > totalPages) {
    startPage -= endPage - totalPages;
    endPage = totalPages;
  }

  const pages = Array.from(
    { length: endPage - startPage + 1 },
    (_, i) => startPage + i
  );

  return (
    <nav>
      <ul className={styles.pagination}>
        {
          <li
            className={styles.page_item}
            onClick={() => setIsPage(prevPage)}
            style={{ opacity: prevPage ? 1 : 0 }}
          >
            <Link href={`/review/list/${prevPage}`} passHref>
              <div className={styles.page_link} aria-label="Previous" />
            </Link>
          </li>
        }

        {pages.map((page) => (
          <li
            key={page}
            onClick={() => setIsPage(page)}
            className={`${styles["page_item"]} ${
              isPage === page ? styles.active : ""
            }`}
          >
            <Link href={`/review/list/${page}`} passHref>
              <div className={styles.page_link}>{page}</div>
            </Link>
          </li>
        ))}

        {
          <li
            className={styles.page_item}
            onClick={() => setIsPage(nextPage)}
            style={{ opacity: prevPage ? 1 : 0 }}
          >
            <Link href={`/review/list/${nextPage}`} passHref>
              <div className={styles.page_link} aria-label="Next" />
            </Link>
          </li>
        }
      </ul>
    </nav>
  );
};

export default Pagination;

export const getStaticPaths: GetStaticPaths = async () => {
  return {
    paths: [],
    fallback: "blocking",
  };
};

export const getStaticProps: GetStaticProps = async ({ params }) => {
  try {
    const user_id = params?.id as string;
    //worldcups/1

    const res = await fetch(
      `${BACK_URL}users/${user_id}/reviews?page=1&size=10`
    ); //수정해야함
    console.log(res, "res");
    const data = await res.json();

    if (res.status === 404) {
      return { notFound: true };
    }

    return { props: { data } };
  } catch (error) {
    console.error("An error occurred during the fetch request:", error);
    return { props: {} };
  }
};
