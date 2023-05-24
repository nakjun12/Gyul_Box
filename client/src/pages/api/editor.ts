import { BACK_URL } from "../../utils/ConfigHelper";
import type { Editor_type } from "../../utils/types/types";
const review_create = async (review: Editor_type) => {
  const response = await fetch(`${BACK_URL}/reviews?userId=${1}`, {
    method: "POST",
    headers: {
      "ngrok-skip-browser-warning": "true",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(review),
  });
  const data = await response.json();
  return data;
};

export { review_create };
