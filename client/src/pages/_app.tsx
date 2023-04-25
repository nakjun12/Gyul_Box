import { Footer } from "../component/templates/footer/Footer";
import Header from "../component/templates/header/Header";

import type { AppProps } from "next/app";
import { RecoilRoot } from "recoil";
import "../styles/globals.scss";
import styles from "./../styles/Home.module.scss";

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
