import type { AppProps } from "next/app";
import { RecoilRoot } from "recoil";
import "../styles/globals.scss";
import Header from "@/component/organisms/header/Header";
import Footer from "@/component/organisms/footer/footer";
import styles from "../pages/home.module.scss";

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <>
      <RecoilRoot>
        <Header />
        <div className={styles.home}>
          <Component {...pageProps} />
        </div>
        <Footer />
      </RecoilRoot>
    </>
  );
}

export default MyApp;
