import React, { useState } from "react";
import { Button } from "@mui/material";
const Counter = () => {
  const [count, setCount] = useState(0);
  const handleIncreament = () => {
    setCount(count + 1);
  };

  const handleDecreament = () => {
    setCount((ans) => ans - 1);
  };

  return (
    <div className="counter">
      <h1>Counter</h1>
      <div
        style={{
          display: "flex",
          flexDirection: "row",
          justifyContent: "center",
        }}
      >
        <Button onClick={handleDecreament}>DEC</Button>
        <h1>{count}</h1>
        <Button onClick={handleIncreament}>INC</Button>
      </div>
    </div>
  );
};

export default Counter;
