import {
  Box,
  Card,
  CardHeader,
  Divider,
  Grid,
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
import { statistics, goals } from "src/view/Tournaments/TournamentsServices";
import { HeaderHomepage } from "src/layouts/dashboard/header";
import { FooterHomepage } from "src/layouts/dashboard/footer";
import { Ads } from "src/layouts/dashboard/ads";

import { getAllTournaments } from "src/view/Tournaments/TournamentsServices";
import { format } from "date-fns";
import { getAllPlayer } from "src/view/ManagePlayer/ManagePlayerServices";
import { getAllMatch, getTopScorers } from "src/view/ManageCalendar/ManageCalendarServices";

const Homepage = () => {
  const [listItems, setlistItems] = useState([]);
  const [listItemsScores, setlistItemsScores] = useState([]);
  const [listItemsGoals, setlistItemsGoals] = useState([]);
  const [listItemsTournaments, setlistItemsTournaments] = useState([]);
  const [listItemsPlayers, setlistItemsPlayers] = useState([]);
  const [listItemsMatches, setlistItemsMatches] = useState([]);

  const getStatistics = async () => {
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

  const getGoals = async () => {
    try {
      const data = await goals();
      setlistItemsGoals(data?.data);
    } catch (error) {}
  };

  const matches = async () => {
    try {
      const data = await getAllMatch();
      setlistItemsMatches(data?.data.filter((match) => match.loaiTranDau === "chinhthuc"));
    } catch (error) {}
  };

  const tournaments = async () => {
    try {
      const data = await getAllTournaments();
      setlistItemsTournaments(data?.data);
    } catch (error) {}
  };

  const players = async () => {
    try {
      const data = await getAllPlayer();
      setlistItemsPlayers(data?.data);
    } catch (error) {}
  };

  useEffect(() => {
    getGoals();
    getStatistics();
    scorers();
    tournaments();
    players();
    matches();
  }, []);

  const cardStyle = {
    borderRadius: 2,
    boxShadow: "0px 4px 20px rgba(0, 0, 0, 0.1)",
    marginBottom: 4,
    padding: 3,
    background:
      "#232324 url(https://xoilacziv.tv/wp-content/themes/bongda/dist/images/bg-match/bg-match.png) no-repeat bottom",
  };

  const headerStyle = {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    padding: 0,
  };

  return (
    <div className="homepage">
      <HeaderHomepage />

      <Box
        component="img"
        src="https://cdn.lfastcdn.com/2024/11/13asd8.gif"
        alt="8XBET A PC"
        sx={{
          marginTop: 3,
          width: "100%", // Ensures the image is responsive
          height: "auto", // Maintains aspect ratio
          display: "block", // Removes any default inline styling gaps
        }}
      />

      <Box sx={{ padding: 3 }}>
        {/* Card for Match Statistics */}
        <Card sx={cardStyle}>
          <Box sx={headerStyle}>
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
              <Table sx={{ borderCollapse: "collapse", width: "100%" }}>
                <TableHead>
                  <TableRow>
                    {["Team Name", "Matches", "Total Wins", "Points", "Tournaments"].map(
                      (header, index) => (
                        <TableCell
                          key={index}
                          align={index === 0 ? "left" : "center"}
                          sx={{
                            fontWeight: "bold",
                            fontSize: "16px",
                            color: "#555",
                            padding: "12px 16px",
                            textTransform: "uppercase",
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
                        <Typography variant="body1" sx={{ color: "gray", padding: 3 }}>
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
                              align="left"
                              sx={{
                                maxWidth: "200px",
                                wordBreak: "break-word",
                                fontWeight: "500",
                                color: "#333",
                                padding: "12px 16px",
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
        </Card>

        <Grid container spacing={3}>
          <Grid
            item
            xs={12}
            md={4}
            sx={{
              marginBottom: 4,
            }}
          >
            <Card sx={cardStyle}>
              <CardHeader
                title="Top Scorers"
                titleTypographyProps={{
                  variant: "h5",
                  sx: { fontWeight: "bold", color: "#333" },
                }}
              />
              <List>
                {listItemsScores.map((score, index) => {
                  const hasDivider = index < listItemsScores.length - 1;
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
                        secondary={`Got ${score.goal} goals`}
                        secondaryTypographyProps={{
                          variant: "body2",
                          sx: { color: "#666" },
                        }}
                      />
                    </ListItem>
                  );
                })}
              </List>
            </Card>
          </Grid>

          <Grid item xs={12} md={8}>
            <Card sx={cardStyle}>
              <CardHeader
                title="Players"
                titleTypographyProps={{
                  variant: "h5",
                  sx: { fontWeight: "bold", color: "#333" },
                }}
              />
              <Scrollbar>
                <Table>
                  <TableHead>
                    <TableRow>
                      {["Full name", "Team", "Position", "Jersey Number", "Date of birth"].map(
                        (header, index) => (
                          <TableCell
                            key={index}
                            align={index === 0 ? "left" : "center"}
                            sx={{
                              fontWeight: "bold",
                              fontSize: "16px",
                              color: "#555",
                              padding: "12px 16px",
                              textTransform: "uppercase",
                            }}
                          >
                            {header}
                          </TableCell>
                        )
                      )}
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    {listItemsPlayers.map((player) => (
                      <TableRow key={player.id} hover>
                        <TableCell align="left">{player.fullName}</TableCell>
                        <TableCell align="center">{player?.team?.teamName}</TableCell>
                        <TableCell align="center">{player.position}</TableCell>
                        <TableCell align="center">{player.jerseyNumber}</TableCell>
                        <TableCell align="center">
                          {format(new Date(player?.dateOfBirth), "dd/MM/yyyy")}
                        </TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </Scrollbar>
            </Card>
          </Grid>
        </Grid>

        {/* Card for Goals Statistics */}
        <Card sx={cardStyle}>
          <Box sx={{ display: "flex", justifyContent: "space-between" }}>
            <CardHeader
              title="Goals Statistics"
              titleTypographyProps={{
                variant: "h5",
                sx: { fontWeight: "bold", color: "#333" },
              }}
            />
          </Box>
          <Scrollbar sx={{ flexGrow: 1 }}>
            <Box>
              <Table sx={{ borderCollapse: "collapse", width: "100%" }}>
                <TableHead>
                  <TableRow>
                    {["Home Team", "Home Goals", "", "Yellow Card Home", "Tournament"].map(
                      (header, index) => (
                        <TableCell
                          key={index}
                          align={index === 0 ? "left" : "center"}
                          sx={{
                            backgroundColor: "#EBEEFE",
                            fontWeight: "bold",
                            color: "#333",
                            fontSize: "16px",
                            padding: "12px 16px",
                            textTransform: "uppercase",
                          }}
                        >
                          {header}
                        </TableCell>
                      )
                    )}
                  </TableRow>
                </TableHead>
                <TableBody>
                  {listItemsGoals.length <= 0 ? (
                    <TableRow>
                      <TableCell colSpan={5} align="center" sx={{ padding: "20px" }}>
                        <Typography variant="body1" sx={{ color: "gray" }}>
                          No data found
                        </Typography>
                      </TableCell>
                    </TableRow>
                  ) : (
                    listItemsGoals
                      .sort((a, b) => b?.point - a?.point)
                      ?.map((goal) => (
                        <TableRow
                          hover
                          key={goal.id}
                          sx={{ "&:hover": { backgroundColor: "#f9f9f9" } }}
                        >
                          <TableCell sx={{ minWidth: "150px", padding: "12px 16px" }}>
                            {goal.homeTeamName}
                          </TableCell>
                          <TableCell align="center">{goal.totalGoalHome}</TableCell>
                          <TableCell align="center">{goal.totalRedHome}</TableCell>
                          <TableCell align="center">{goal.totalYellowHome}</TableCell>
                          <TableCell align="center">{goal.nametour}</TableCell>
                        </TableRow>
                      ))
                  )}
                </TableBody>
              </Table>
            </Box>
          </Scrollbar>
        </Card>

        {/* Card for Tournaments */}
        <Card sx={cardStyle}>
          <Box sx={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
            <CardHeader
              title="Tournaments"
              titleTypographyProps={{
                variant: "h5",
                sx: { fontWeight: "bold", color: "#333" },
              }}
            />
          </Box>
          <Scrollbar sx={{ flexGrow: 1 }}>
            <Box>
              <Table sx={{ borderCollapse: "collapse", width: "100%" }}>
                <TableHead>
                  <TableRow>
                    {["Tournaments", "Start Date", "End Date"].map((header, index) => (
                      <TableCell
                        key={index}
                        align={index === 0 ? "left" : "center"}
                        sx={{
                          backgroundColor: "#EBEEFE",
                          fontWeight: "bold",
                          fontSize: "16px",
                          color: "#555",
                          padding: "12px 16px",
                          textTransform: "uppercase",
                        }}
                      >
                        {header}
                      </TableCell>
                    ))}
                  </TableRow>
                </TableHead>
                <TableBody>
                  {listItemsTournaments.length <= 0 ? (
                    <TableRow>
                      <TableCell colSpan={5} align="center">
                        <Typography variant="body1" sx={{ color: "gray", padding: 3 }}>
                          No data found
                        </Typography>
                      </TableCell>
                    </TableRow>
                  ) : (
                    listItemsTournaments
                      .sort((a, b) => b?.point - a?.point)
                      ?.map((tournament) => {
                        return (
                          <TableRow
                            hover
                            key={tournament?.id}
                            sx={{
                              "&:hover": { backgroundColor: "#f9f9f9" },
                            }}
                          >
                            <TableCell
                              align="left"
                              sx={{
                                maxWidth: "200px",
                                wordBreak: "break-word",
                                fontWeight: "500",
                                color: "#333",
                                padding: "12px 16px",
                              }}
                            >
                              {tournament.tournamentsName}
                            </TableCell>
                            <TableCell align="center">
                              {format(new Date(tournament?.startDate), "dd/MM/yyyy")}
                            </TableCell>
                            <TableCell align="center">
                              {format(new Date(tournament?.endDate), "dd/MM/yyyy")}
                            </TableCell>
                          </TableRow>
                        );
                      })
                  )}
                </TableBody>
              </Table>
            </Box>
          </Scrollbar>
        </Card>

        {/* Card for Official match schedule */}
        <Card sx={cardStyle}>
          <Box sx={{ display: "flex", justifyContent: "space-between" }}>
            <CardHeader
              title="Official match schedule"
              titleTypographyProps={{
                variant: "h5",
                sx: { fontWeight: "bold", color: "#333" },
              }}
            />
          </Box>
          <Scrollbar sx={{ flexGrow: 1 }}>
            <Box>
              <Table sx={{ borderCollapse: "collapse", width: "100%" }}>
                <TableHead>
                  <TableRow>
                    {[
                      "Match Date",
                      "Home Team",
                      "Away Team",
                      "Home Team Score",
                      "Away Team Score",
                    ].map((header, index) => (
                      <TableCell
                        key={index}
                        align={index === 0 ? "left" : "center"}
                        sx={{
                          backgroundColor: "#EBEEFE",
                          fontWeight: "bold",
                          color: "#333",
                          fontSize: "16px",
                          padding: "12px 16px",
                          textTransform: "uppercase",
                        }}
                      >
                        {header}
                      </TableCell>
                    ))}
                  </TableRow>
                </TableHead>
                <TableBody>
                  {listItemsMatches.length <= 0 ? (
                    <TableRow>
                      <TableCell colSpan={5} align="center" sx={{ padding: "20px" }}>
                        <Typography variant="body1" sx={{ color: "gray" }}>
                          No data found
                        </Typography>
                      </TableCell>
                    </TableRow>
                  ) : (
                    listItemsMatches
                      .sort((a, b) => b?.point - a?.point)
                      ?.map((match) => (
                        <TableRow
                          hover
                          key={match.id}
                          sx={{ "&:hover": { backgroundColor: "#f9f9f9" } }}
                        >
                          <TableCell sx={{ minWidth: "150px", padding: "12px 16px" }}>
                            {format(new Date(match?.matchDate), "dd/MM/yyyy")}
                          </TableCell>
                          <TableCell align="center">{match.homeTeam.teamName}</TableCell>
                          <TableCell align="center">{match.awayTeam.teamAwayName}</TableCell>
                          <TableCell align="center">{match.homeTeamScore}</TableCell>
                          <TableCell align="center">{match.awayTeamScore}</TableCell>
                        </TableRow>
                      ))
                  )}
                </TableBody>
              </Table>
            </Box>
          </Scrollbar>
          <Divider sx={{ marginTop: 2 }} />
        </Card>
      </Box>
      <iframe
        src="https://chatboxn.com/room/6362206e7e42f3850b75692c/football"
        width="100%"
        height="513"
        allowtransparency="yes"
        allow="autoplay"
        frameborder="0"
        marginheight="0"
        marginwidth="0"
        scrolling="auto"
      ></iframe>
      <FooterHomepage />
      <Ads />
    </div>
  );
};

export default Homepage;
