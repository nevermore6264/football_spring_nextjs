import { Box, Stack } from "@mui/material";

const SIDE_NAV_WIDTH = 0;
const TOP_NAV_HEIGHT = 64;

export const HeaderHomepage = (props) => {
  return (
    <>
      <Box
        component="header"
        sx={{
          background:
            "linear-gradient(113deg, #1553ef 7.37%, #0c3089 57.22%, #0b2a79 66.05%, #000 131.67%)",
          position: "sticky",
          left: {
            lg: `${SIDE_NAV_WIDTH}px`,
          },
          top: 0,
          width: {
            lg: `calc(100% - ${SIDE_NAV_WIDTH}px)`,
          },
          zIndex: (theme) => theme.zIndex.appBar,
          boxShadow: "0 2px 4px rgba(0, 0, 0, 0.1)", // Subtle shadow
        }}
      >
        <Stack
          direction="row"
          alignItems="center"
          justifyContent="space-between" // Align logo to the left and text to the center
          sx={{
            minHeight: TOP_NAV_HEIGHT,
            px: 3,
          }}
        >
          {/* Logo Section */}
          <Box
            component="img"
            src="https://w7.pngwing.com/pngs/356/930/png-transparent-green-cleat-and-soccer-ball-illustration-english-football-league-logo-football-boot-sport-high-resolution-football-grass-sports-equipment-adidas.png"
            alt="Logo"
            sx={{
              height: 40,
              width: "auto",
            }}
          />

          {/* Title Section */}
          <Box
            component="div"
            sx={{
              fontSize: "24px", // Font size of the title
              fontWeight: "bold",
              color: "#ffffff",
              textAlign: "center",
              flex: 1, // Allow the text to take available space
              marginLeft: 2,
            }}
          >
            Football Tournament Management System
          </Box>
        </Stack>
      </Box>
    </>
  );
};
