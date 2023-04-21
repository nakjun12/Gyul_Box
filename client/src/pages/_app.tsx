import Footer from "@/component/organisms/footer/footer";
import Header from "@/component/templates/header/Header";

import styles from "@/styles/home.module.scss";
import type { AppProps } from "next/app";
import { RecoilRoot } from "recoil";
import "../styles/globals.scss";

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <>
      <RecoilRoot>
        <div className={styles.bodyWrapper}>
          <Header />
          <div className={styles.home}>
            <Component {...pageProps} />
          </div>
          <Footer />
        </div>
      </RecoilRoot>
    </>
  );
}

export default MyApp;
