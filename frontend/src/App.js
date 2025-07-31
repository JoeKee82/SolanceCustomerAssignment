import React, { StrictMode, useState, useEffect } from "react";
import { AllCommunityModule, ModuleRegistry } from "ag-grid-community";
import { createRoot } from "react-dom/client";
import { AgGridReact } from "ag-grid-react";

ModuleRegistry.registerModules([AllCommunityModule]);

const TransactionTable = () => {
  const [rowData, setRowData] = useState([]);
  useEffect(() => {
    fetch("/transaction")
      .then((response) => response.json())
      .then((data) => setRowData(data))
      .catch((error) => console.error("Error fetching transactions:", error));
  }, []);

  const [columnDefs, setColumns] = useState([
    { field: "txnId" },
    { field: "userId" },
    { field: "solanceFrom" },
    { field: "solanceTo" },
    { field: "amountBuy" },
    { field: "amountSell" },
    { field: "rate" },
    { field: "operationType" },
    { field: "depositorIban" },
    { field: "beneficiaryId" },
    { field: "beneficiaryIban" },
    { field: "paymentRef" },
    { field: "purposeRef" },
    { field: "originatingCountry" },
    { field: "dateTimeEntered" }
  ]);

  const gridOptions = {
    defaultColDef: {
      flex: 1,
      cellStyle: { fontWeight: "bold" },
    },
  };

  return (
    <div style={{ width: "100%", height: "100%" }}>
      <AgGridReact
        rowData={rowData}
        columnDefs={columnDefs}
        gridOptions={gridOptions}
      />
    </div>
  );
};

const root = createRoot(document.getElementById("root"));
root.render(
  <StrictMode>
    <TransactionTable />
  </StrictMode>
);

export default TransactionTable;
