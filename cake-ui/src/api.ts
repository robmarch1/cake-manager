const rootUrl = process.env.REACT_APP_API_ROOT_URL;
console.log('Root URL: ' + rootUrl);

export function endpoint(path: string) {
  return rootUrl + path;
}
