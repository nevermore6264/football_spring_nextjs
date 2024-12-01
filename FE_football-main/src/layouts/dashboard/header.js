import { Box, Button, Stack } from "@mui/material";
import { alpha } from "@mui/material/styles";

const SIDE_NAV_WIDTH = 280;
const TOP_NAV_HEIGHT = 64;

export const HeaderHomepage = (props) => {
  const handleButtonClick = () => {
    window.location.href = "/";
  };

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
          boxShadow: "0 2px 4px rgba(0, 0, 0, 0.1)", // Thêm shadow nhẹ
        }}
        className="banner-logo"
      >
        <Stack
          alignItems="center"
          direction="row"
          justifyContent="space-between"
          spacing={2}
          sx={{
            minHeight: TOP_NAV_HEIGHT,
            px: 2,
          }}
        >
          <Box
            component="div"
            sx={{
              fontSize: "20px",
              fontWeight: "bold",
              color: "#ffffff",
              display: "flex",
              alignItems: "center",
            }}
          >
            Football Tournament Management System
          </Box>
          <Button
            onClick={handleButtonClick}
            sx={{
              backgroundColor: "#3f51b5",
              color: "#fff",
              fontSize: "14px",
              fontWeight: "bold",
              textTransform: "none", // Giữ chữ không viết hoa
              padding: "8px 16px",
              borderRadius: "8px",
              "&:hover": {
                backgroundColor: "#303f9f", // Màu khi hover
              },
            }}
          >
            Sign In
          </Button>
        </Stack>
      </Box>
    </>
  );
};
