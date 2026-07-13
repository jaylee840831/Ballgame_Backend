#!/bin/bash

# 定義變數方便管理
CONTAINER_NAME="ballgame-backend"
IMAGE_NAME="ballgame-backend-image"
PORT_MAPPING="8080:8080"

echo "============ 🚀 開始執行自動部署 ============"

# 1. 檢查並刪除舊的容器
if [ -n "$(docker ps -aq --filter "name=${CONTAINER_NAME}")" ]; then
    echo "⚙️ 偵測到現有的容器 '${CONTAINER_NAME}'，正在停止並刪除..."
    docker rm -f $CONTAINER_NAME
    echo "✅ 舊容器已成功移除。"
else
    echo "ℹ️ 沒有偵測到名為 '${CONTAINER_NAME}' 的容器，跳過刪除步驟。"
fi

# 2. 執行 Docker Build 建立新映像檔
echo "📦 開始編譯 Docker 映像檔..."
docker build -t $IMAGE_NAME .

# 檢查建置是否成功
if [ $? -ne 0 ]; then
    echo "❌ 錯誤：Docker 建置失敗，終止執行。"
    exit 1
fi
echo "✅ 映像檔 '${IMAGE_NAME}' 建置成功。"

# 3. 執行 Docker Run 啟動新容器
echo "🌐 正在啟動新容器 '${CONTAINER_NAME}'..."
docker run -d \
  --name $CONTAINER_NAME \
  -p $PORT_MAPPING \
  --restart unless-stopped \
  $IMAGE_NAME

# 檢查啟動是否成功
if [ $? -eq 0 ]; then
    echo "=========================================="
    echo "🎉 部署成功！"
    echo "📱 容器名稱：$CONTAINER_NAME"
    echo "🔗 瀏覽網址：http://localhost:${PORT_MAPPING%%:*}"
    echo "=========================================="
else
    echo "❌ 錯誤：容器啟動失敗。"
    exit 1
fi
