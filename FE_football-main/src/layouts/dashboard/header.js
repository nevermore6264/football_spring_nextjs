import { Box, Button, Stack } from "@mui/material";
import { alpha } from "@mui/material/styles";
import { useNavigate } from "react-router-dom"; // Import useNavigate

const SIDE_NAV_WIDTH = 280;
const TOP_NAV_HEIGHT = 64;

export const Header = (props) => {
  const navigate = useNavigate(); // Khởi tạo useNavigate

  const handleButtonClick = () => {
    navigate("/"); // Điều hướng sang trang chủ
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
          <Stack alignItems="center" direction="row" spacing={2}>
            <Button
              onClick={handleButtonClick} // Gọi hàm khi nhấn vào nút
              sx={{
                cursor: "pointer",
                height: 40,
                width: 40,
              }}
              title="Sign in"
            >
              Sign In
            </Button>
          </Stack>
        </Stack>
      </Box>
    </>
  );
};
