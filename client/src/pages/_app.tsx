import type { AppProps } from "next/app";
import { RecoilRoot } from "recoil";
import "../styles/globals.scss";

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <RecoilRoot>
      <Component {...pageProps} />
    </RecoilRoot>
  );
}

export default MyApp;
