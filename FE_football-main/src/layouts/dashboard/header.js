import { Box, Stack } from "@mui/material";
import { alpha } from "@mui/material/styles";

const SIDE_NAV_WIDTH = 0;
const TOP_NAV_HEIGHT = 64;

export const HeaderHomepage = (props) => {
  return (
    <>
      <Box
        component="header"
        sx={{
          backdropFilter: "blur(6px)",
          backgroundColor: (theme) => alpha(theme.palette.background.default, 0.8),
          position: "sticky",
          left: {
            lg: `${SIDE_NAV_WIDTH}px`,
          },
          top: 0,
          width: {
            lg: `calc(100% - ${SIDE_NAV_WIDTH}px)`,
          },
          zIndex: (theme) => theme.zIndex.appBar,
          boxShadow: "0 2px 4px rgba(0, 0, 0, 0.1)", // Shadow nhẹ
        }}
        className="banner-logo"
      >
        <Stack
          alignItems="center"
          justifyContent="center" // Đặt nội dung ở giữa
          sx={{
            minHeight: TOP_NAV_HEIGHT,
            px: 2,
          }}
        >
          <Box
            component="div"
            sx={{
              fontSize: "36px", // Làm chữ to hơn
              fontWeight: "bold",
              color: "#ffffff",
              textAlign: "center", // Căn giữa chữ
            }}
          >
            Football Tournament Management System
          </Box>
        </Stack>
      </Box>
    </>
  );
};
