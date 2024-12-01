import {
  Box,
  Card,
  CardHeader,
  Divider,
  List,
  ListItem,
  ListItemAvatar,
  ListItemText,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography,
} from "@mui/material";
import { Scrollbar } from "src/components/scrollbar";
import { SeverityPill } from "src/components/severity-pill";
import { useEffect, useState } from "react";
import { statistics } from "src/view/Tournaments/TournamentsServices";
import { getTopScorers } from "src/view/ManageCalendar/ManageCalendarServices";

const Homepage = () => {
  const [listItems, setlistItems] = useState([]);
  const [listItemsScores, setlistItemsScores] = useState([]);

  const getAllTournament = async () => {
    try {
      const data = await statistics();
      setlistItems(data?.data);
    } catch (error) {}
  };

  const convertDataScore = (value) => {
    return {
      id: value?.idplayer,
      image: value?.photo,
      name: value?.fullName,
    };
  };

  const scorers = async () => {
    try {
      const data = await getTopScorers();
      const topScorers = data?.data?.map((i) => {
        return {
          ...convertDataScore(i?.player),
          goal: i?.goalsScored,
        };
      });
      setlistItemsScores(topScorers);
    } catch (error) {}
  };

  useEffect(() => {
    getAllTournament();
    scorers();
  }, []);

  return (
    <>
      <Card
        sx={{
          borderRadius: 2,
          boxShadow: "0px 4px 20px rgba(0, 0, 0, 0.1)",
          marginBottom: 4,
          padding: 3,
        }}
      >
        <Box
          sx={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
          }}
        >
          <CardHeader
            title="Match Statistics"
            titleTypographyProps={{
              variant: "h5",
              sx: { fontWeight: "bold", color: "#333" },
            }}
          />
        </Box>
        <Scrollbar sx={{ flexGrow: 1 }}>
          <Box>
            <Table>
              <TableHead>
                <TableRow>
                  {["Team Name", "Matches", "Total Wins", "Points", "Tournaments"].map(
                    (header, index) => (
                      <TableCell
                        key={index}
                        align="center"
                        sx={{
                          backgroundColor: "#EBEEFE",
                          fontWeight: "bold",
                          fontSize: "16px",
                          color: "#555",
                        }}
                      >
                        {header}
                      </TableCell>
                    )
                  )}
                </TableRow>
              </TableHead>
              <TableBody>
                {listItems.length <= 0 ? (
                  <TableRow>
                    <TableCell colSpan={5} align="center">
                      <Typography
                        variant="body1"
                        sx={{ color: "gray", padding: 3 }}
                      >
                        No data found
                      </Typography>
                    </TableCell>
                  </TableRow>
                ) : (
                  listItems
                    .sort((a, b) => b?.point - a?.point)
                    ?.map((order) => {
                      return (
                        <TableRow
                          hover
                          key={order?.id}
                          sx={{
                            "&:hover": { backgroundColor: "#f9f9f9" },
                          }}
                        >
                          <TableCell
                            align="center"
                            sx={{
                              maxWidth: "200px",
                              wordBreak: "break-word",
                              fontWeight: "500",
                              color: "#333",
                            }}
                          >
                            {order.teamName}
                          </TableCell>
                          <TableCell align="center">{order?.matches}</TableCell>
                          <TableCell align="center">{order?.totalWins}</TableCell>
                          <TableCell align="center">
                            <SeverityPill color={"error"}>{order?.point}</SeverityPill>
                          </TableCell>
                          <TableCell align="center">{order?.tournaments}</TableCell>
                        </TableRow>
                      );
                    })
                )}
              </TableBody>
            </Table>
          </Box>
        </Scrollbar>
        <Divider sx={{ marginTop: 2 }} />
      </Card>

      <Card
        sx={{
          borderRadius: 2,
          boxShadow: "0px 4px 20px rgba(0, 0, 0, 0.1)",
          padding: 3,
        }}
      >
        <Box
          sx={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
          }}
        >
          <CardHeader
            title="Top Scorers"
            titleTypographyProps={{
              variant: "h5",
              sx: { fontWeight: "bold", color: "#333" },
            }}
          />
        </Box>
        <List>
          {listItemsScores.map((score, index) => {
            const hasDivider = index < listItemsScores.length - 1;
            const goal = score?.goal;
            return (
              <ListItem divider={hasDivider} key={score.id}>
                <ListItemAvatar>
                  {score.image ? (
                    <Box
                      component="img"
                      src={score.image}
                      sx={{
                        borderRadius: "50%",
                        height: 48,
                        width: 48,
                        border: "2px solid #ddd",
                      }}
                    />
                  ) : (
                    <Box
                      sx={{
                        borderRadius: "50%",
                        backgroundColor: "neutral.200",
                        height: 48,
                        width: 48,
                      }}
                    />
                  )}
                </ListItemAvatar>
                <ListItemText
                  primary={score.name}
                  primaryTypographyProps={{
                    variant: "subtitle1",
                    sx: { fontWeight: "500", color: "#333" },
                  }}
                  secondary={`Got ${goal} goals`}
                  secondaryTypographyProps={{
                    variant: "body2",
                    sx: { color: "#666" },
                  }}
                />
              </ListItem>
            );
          })}
        </List>
        <Divider sx={{ marginTop: 2 }} />
      </Card>
    </>
  );
};

export default Homepage;
