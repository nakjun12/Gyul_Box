import Link from "next/link";
import { useState } from "react";
import styles from "./PagiNation.module.scss";
interface PaginationProps {
  currentPage: number;
  totalPages: number;
}

const Pagination: React.FC<PaginationProps> = ({ currentPage, totalPages }) => {
  const [isPage, setIsPage] = useState(currentPage);
  const prevPage = isPage > 1 ? isPage - 1 : null;
  const nextPage = isPage < totalPages ? isPage + 1 : null;
  const pageRange = 2;
  let startPage = isPage - pageRange;
  let endPage = isPage + pageRange;

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
  console.log(pages, isPage);
  return (
    <nav>
      <ul className={styles.pagination}>
        {prevPage && (
          <li className={styles.page_item}>
            <Link href={`/review/list?page=${prevPage}`} passHref>
              <div className={styles.page_link} aria-label="Previous" />
            </Link>
          </li>
        )}

        {pages.map((page) => (
          <li
            key={page}
            onClick={() => setIsPage(page)}
            className={`${styles["page_item"]} ${
              isPage === page ? styles.active : ""
            }`}
          >
            <Link href={`/review/list?page=${page}`} passHref>
              <div className={styles.page_link}>{page}</div>
            </Link>
          </li>
        ))}

        {nextPage && (
          <li className={styles.page_item}>
            <Link href={`/review/list?page=${nextPage}`} passHref>
              <div className={styles.page_link} aria-label="Next" />
            </Link>
          </li>
        )}
      </ul>
    </nav>
  );
};

export default Pagination;
