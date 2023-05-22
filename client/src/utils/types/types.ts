interface SatisfactionMenu {
  label: string;
  value: string;
  question: string;
}

interface Review_data {
  reviewId: number;
  advantage: string;
  disadvantage: string;
  avgRate: number;
  reviewLikes: number;
  createdAt: string;
  modifiedAt: string | null;
  address: string;
}
interface Review_simple {
  data: Review_data[];
}

interface Map_data {
  id: number;
  reviewCount: number;
  coordinate: {
    latitude: number;
    longitude: number;
  };
}

interface Map_circle {
  data: Map_data[];
}

interface SearchAddress_data {
  areaCode: number;
  areaName: string;
  coordinate: {
    latitude: number;
    longitude: number;
  };
}

interface SearchAddress {
  data: SearchAddress_data[];
}

export type {
  SatisfactionMenu,
  Review_data,
  Review_simple,
  SearchAddress,
  SearchAddress_data,
  Map_circle,
  Map_data,
};
