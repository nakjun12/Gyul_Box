import useSWR from "swr";

function fetcher(url: string) {
  return fetch(url).then((res) => res.json());
}

function MyComponent() {
  const { data, error } = useSWR("/api/data", fetcher);

  if (error) return <div>Failed to load data</div>;
  if (!data) return <div>Loading...</div>;

  return <div>{data}</div>;
}
