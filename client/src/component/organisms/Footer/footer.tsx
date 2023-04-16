import React from "react";
import styles from "../footer/Footer.module.scss";
import Image from "next/image";
import gyulLogo from "../../../../public/icon/gyulLogo.png";
import facebook from "../../../../public/svg/facebook.svg";
import instagram from "../../../../public/svg/instagram.svg";
import youtube from "../../../../public/svg/youtube.svg";

type Props = {};

export default function Footer({}: Props) {
  return (
    <footer className={styles.wrapper}>
      <div className={styles.infoWrapper}>
        <h2 className={styles.mainContent}>
          <div className={styles.gyulLogo}>
            <Image src={gyulLogo} alt="귤 로고" width={40} height={40} />
          </div>
          GyulBox
        </h2>
        <div className={styles.subContent}>
          귤박스 회사 이념 | 귤박스 수상 내역 | 개인정보처리방침
        </div>
        <div className={styles.subContent}>
          주소 : 서귀포시 성산읍 성산리 1 | 사업자 등록번호 : 010-3816-1435
        </div>
      </div>
      <div className={styles.textWrapper}>
        <div className={styles.textContent}>
          대표이사 | 주식회사 | 주변정보 | 회사방침 | 팀조직도
        </div>
        <div className={styles.textContent}>
          채용정보 | 대표기술 | 사용방침 | 오시는길 | 협력업체
        </div>
      </div>
      <div className={styles.logoWrapper}>
        <div className={styles.imageDiv}>
          <Image src={facebook} alt="페이스북 아이콘" width={35} height={40} />
        </div>
        <div className={styles.imageDiv}>
          <Image
            src={instagram}
            alt="인스타그램 아이콘"
            width={35}
            height={35}
          />
        </div>
        <div className={styles.imageDiv}>
          <Image src={youtube} alt="유튜브 아이콘" width={40} height={40} />
        </div>
      </div>
    </footer>
  );
}
