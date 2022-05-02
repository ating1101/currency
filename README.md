# currency

簡易查詢比特幣與美元、英鎊、歐元匯率

以內建 h2 為 database
儲存各幣別對應的中文名稱

呼叫 coindesk api
取得貨幣與比特幣最新匯率

# 未來預計可調整方向
1. 使用 JWT Token 保護 API
2. 改接其他 database
3. 使用 reids cache server 儲存不常異動的暫存資料
