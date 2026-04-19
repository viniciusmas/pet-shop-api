FROM quay.io/keycloak/keycloak:26.5.0

# Copia o realm para dentro da imagem
COPY realm-pet-shop.json /opt/keycloak/data/import/realm-pet-shop.json

# Gera a distribuição otimizada
RUN /opt/keycloak/bin/kc.sh build

# Inicia o Keycloak e importa o realm
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev", "--import-realm"]