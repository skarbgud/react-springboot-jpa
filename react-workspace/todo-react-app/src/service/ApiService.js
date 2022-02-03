import { API_BASE_URL } from "../api-config";
const ACCESS_TOKEN = "ACCESS_TOKEN";

export function call(api, method, request) {
  let headers = new Headers({
      "Content-Type": "application/json",
  });
  // 로컬 스토리지에서 ACCESS_TOKEN 가져오기
  const accessToken = localStorage.getItem(ACCESS_TOKEN);
  if(accessToken && accessToken != null) {
    headers.append("Authorization", "Bearer " + accessToken);
  }

  let options = {
    headers: headers,
    url: `${API_BASE_URL}${api}`,
    method: method,
  };

  if (request) {
    // GET method
    options.body = JSON.stringify(request);
  }
  return fetch(options.url, options).then((response) =>
    response.json().then((json) => {
      if (!response.ok) {
        // 아니면 에러 response를 받은것.
        return Promise.reject(json);
      }
      // response.ok가 true이면 정상적인 response를 받은것
      return json;
    })
  )
  
  .catch((error) => {
    if (error.status === 403) {
      window.location.href = "/login"; // 접근권한 없을경우 redirect
    }
    return Promise.reject(error);
  });
}

export function signin(userDTO) {
  return call("/auth/signin", "POST", userDTO) 
    .then((response) => {
      // 로컬 스토리지에 토큰 저장
      localStorage.setItem(ACCESS_TOKEN, response.token);
      // token이 존재하면 /로 리다이렉트
      window.location.href = "/";
    })
}