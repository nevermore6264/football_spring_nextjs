import { Box, Typography } from "@mui/material";

export const Ads = (props) => {
  return (
    <>
      <Box
        sx={{
          position: "fixed",
          bottom: 0,
          left: 0,
          zIndex: 1000,
        }}
      >
        <a href="javascript:;" rel="nofollow">
          <img
            src="https://mistinguettmadonnaetmoi.com/wp-content/uploads/2024/07/Gif-2.gif"
            alt="F8bet"
            style={{ width: "350px", height: "auto" }}
          />
        </a>
      </Box>
      <Box
        sx={{
          position: "fixed",
          bottom: 0,
          right: 0,
          zIndex: 1000,
        }}
      >
        <a href="javascript:;" rel="nofollow">
          <img
            src="https://mistinguettmadonnaetmoi.com/wp-content/uploads/2024/07/Gif-1.gif"
            alt="F8bet"
            style={{ width: "350px", height: "auto" }}
          />
        </a>
      </Box>
    </>
  );
};
