import type { AppProps } from "next/app";
import Head from "next/head";
import { RecoilRoot } from "recoil";
import { Footer } from "../component/templates/footer/Footer";
import Header from "../component/templates/header/Header";
import "../styles/globals.scss";
import styles from "./../styles/Home.module.scss";
function MyApp({ Component, pageProps }: AppProps) {
  return (
    <>
      <RecoilRoot>
        <Head>
          <title>GyulBox</title>
          <meta name="description" content="제주도 한달리뷰 알아보자" />
          <meta name="viewport" content="width=device-width, initial-scale=1" />
          <link rel="icon" href="/icon/gyul1.svg" />
          <meta
            name="viewport"
            content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"
          />
        </Head>
        <div className={styles.all}>
          <div className={styles.bodyWrapper}>
            <Header />
            <div className={styles.home}>
              <Component {...pageProps} />
            </div>
            <Footer />
          </div>
        </div>
      </RecoilRoot>
    </>
  );
}

export default MyApp;
