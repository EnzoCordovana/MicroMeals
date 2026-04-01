FROM alpine:3.20

WORKDIR /app

COPY package*.json ./

RUN apk add --update nodejs npm && npm install

COPY data/commandes/ ./data/commandes

EXPOSE 3005

CMD ["npx", "json-server", "--watch", "data/commandes/commandes.json", "--port", "3005", "--host", "0.0.0.0"]