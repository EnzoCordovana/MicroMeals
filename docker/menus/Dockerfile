FROM alpine:3.20

WORKDIR /app

COPY package*.json ./

RUN apk add --update nodejs npm && npm install

COPY data/menus/ ./data/menus

EXPOSE 3004

CMD ["npx", "json-server", "--watch", "data/menus/menus.json", "--port", "3004", "--host", "0.0.0.0"]