import React from "react";
import styles from "../searchBar/SearchBar.module.scss";
import search from "../../../../public/svg/search.svg";
import Image from "next/image";

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
