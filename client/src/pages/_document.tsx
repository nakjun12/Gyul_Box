import type { DocumentContext, DocumentInitialProps } from "next/document";
import Document, { Head, Html, Main, NextScript } from "next/document";
import type { ReactElement } from "react";
import { KAKAKO_JAVASCRIPT } from "../lib/ConfigHelper";
class MyDocument extends Document {
  static async getInitialProps(
    ctx: DocumentContext
  ): Promise<DocumentInitialProps> {
    const initialProps = await Document.getInitialProps(ctx);

    return { ...initialProps };
  }

  render(): ReactElement {
    return (
      <Html lang="ko">
        <Head>
          <meta property="og:image" content="/icon/gyulLogo.png" />
          <meta property="og:title" content="GyulBox" />
          <meta
            property="og:description"
            content="제주도 원룸 정보를 빠르게 알아보자"
          />
          <link rel="icon" href="/favicon.ico" />
          <script
            defer
            type="text/javascript"
            src={`https://dapi.kakao.com/v2/maps/sdk.js?appkey=${KAKAKO_JAVASCRIPT}&autoload=false&libraries=services,clusterer,drawing`}
          />
        </Head>

        <body>
          <Main />
          <NextScript />
        </body>
      </Html>
    );
  }
}

export default MyDocument;
