interface SatisfactionMenu {
  label: string;
  value: string;
  question: string;
}

interface Review_simple {
  data: Review_list_data[];
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

type Editor_type = {
  buildingName: string;
  advantage: string;
  disadvantage: string;
  adminCost: string;
  residenceYear: string;
  floor: string;
  buildingType: string;
  rate: Rate;
  address: string;
};
interface Rate {
  interiorRate: number;
  buildingRate: number;
  trafficRate: number;
  securityRate: number;
  locationRate: number;
  avgRate: number | null;
}

type Rating = number;

type writer = {
  id: number;
  nickname: string;
  profileImageUrl: string | null;
};

type Review_detail = {
  reviewId: number;
  buildingName: string;
  advantage: string;
  disadvantage: string;
  adminCost: string;
  residenceYear: string;
  floor: string;
  avgRate: 5;
  likes: 1;
  createdAt: string;
  modifiedAt: string | null;
  writer: writer;
};

type Detail_data = {
  id: number;
  houseName: string;
  buildUes: string;
  buildingStructure: string;
  houseHold: number;
  useAprDay: string;
  floor: string;
  elevator: number;
  platPlc: string;
  coordinate: {
    latitude: number;
    longitude: number;
  };
  rate: {
    interiorRate: Rating;
    buildingRate: Rating;
    trafficRate: Rating;
    securityRate: Rating;
    locationRate: Rating;
    avgRate: Rating;
  };
  reviews: Review_detail[];
};

type Review_list_data = {
  reviewId: number;
  advantage: string;
  disadvantage: string;
  avgRate: number;
  likes: number;
  createdAt: string;
  modifiedAt: string | null;
  address: string;
};

type Review_list_pageInfo = {
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
};

type Review_list = {
  data: Review_list_data[];
  pageInfo: Review_list_pageInfo;
};

export type {
  Review_list_data,
  Review_list,
  Detail_data,
  Review_detail,
  writer,
  Rate,
  Editor_type,
  SatisfactionMenu,
  Review_simple,
  SearchAddress,
  SearchAddress_data,
  Map_circle,
  Map_data,
};
