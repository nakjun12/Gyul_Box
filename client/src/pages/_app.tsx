import type { AppProps } from "next/app";
import { RecoilRoot } from "recoil";
import "../styles/globals.scss";
import Header from "@/component/organisms/header/header";
import Footer from "@/component/organisms/footer/footer";
import styles from "../pages/home.module.scss";

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
