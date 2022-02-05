let backendHost;

const hostname = window && window.location && window.location.hostname;

// 브라우저의 도메인이 local인경우 locahost로 설정
if (hostname === 'localhost') {
  backendHost = 'http://localhost:8080';
} else {
  backendHost = 'https://www.reactbootjpa.tk';
}

export const API_BASE_URL = `${backendHost}`;
