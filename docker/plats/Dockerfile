FROM alpine:3.20

WORKDIR /app

COPY package*.json ./

RUN apk add --update nodejs npm && npm install

COPY data/plats/ ./data/plats

EXPOSE 3003

CMD ["npx", "json-server", "--watch", "data/plats/plats-utilisateurs.json", "--port", "3003", "--host", "0.0.0.0"]