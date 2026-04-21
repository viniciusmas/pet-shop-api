#!/bin/bash

NAMESPACE="pet-shop"

echo "🚀 Iniciando deploy do PetShop Backend + PostgreSQL no Kubernetes..."

# Verifica se o kubectl está instalado
if ! command -v kubectl &> /dev/null
then
    echo "❌ kubectl não encontrado. Instale antes de continuar."
    exit 1
fi

echo "🔍 Verificando se o namespace '$NAMESPACE' existe..."

# Verifica se o namespace existe
if kubectl get namespace "$NAMESPACE" > /dev/null 2>&1; then
    echo "✔ Namespace '$NAMESPACE' já existe."
else
    echo "📁 Namespace '$NAMESPACE' não existe. Criando..."
    kubectl create namespace "$NAMESPACE"
    echo "✔ Namespace criado com sucesso."
fi

kubectl config set-context --current --namespace=pet-shop

echo "❌ Deletando o deployment e service"
kubectl delete deployment --all
kubectl delete service --all

echo "📦 Aplicando configuração do PostgreSQL..."
kubectl apply -f deployment-db.yaml

echo "🔐 Aplicando configuração do Keycloak..."
kubectl apply -f deployment-keycloak.yaml

echo "🐶 Aplicando configuração do frontend PetShop..."
kubectl apply -f deployment-frontend.yaml

echo "🐶 Aplicando configuração do backend PetShop..."
kubectl apply -f deployment-api.yaml

echo "⏳ Aguardando pods iniciarem..."
kubectl get pods -n "$NAMESPACE"

echo "🎉 Deploy concluído!"
echo "Use 'kubectl get svc' para ver os serviços expostos."
