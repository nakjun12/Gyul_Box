import Image from "next/image";
import search from "../../../../public/svg/search.svg";
import styles from "./SearchBar.module.scss";

type Props = {};

export default function SearchBar({}: Props) {
  return (
    <div className={styles.SearchBarDiv}>
      <div className={styles.aSearchBarDiv}>
        <input className={styles.searchBarInput} />
        <Image src={search} alt="돋보기" width={17} height={17}></Image>
      </div>
    </div>
  );
}
