import {
  Box,
  Card,
  CardHeader,
  Divider,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
} from "@mui/material";
import { Scrollbar } from "src/components/scrollbar";
import { SeverityPill } from "src/components/severity-pill";
import { useEffect, useState } from "react";
import { statistics } from "src/view/Tournaments/TournamentsServices";

const Homepage = (props) => {
  const [listItems, setlistItems] = useState([]);

  const getAllTournament = async () => {
    try {
      const data = await statistics();
      setlistItems(data?.data);
    } catch (error) {}
  };

  useEffect(() => {
    getAllTournament();
  }, []);

  return (
    <Card className="form-container">
      <Box sx={{ display: "flex", justifyContent: "space-between" }} className="form-group">
        <CardHeader title="Match statistics" />
      </Box>
      <Scrollbar sx={{ flexGrow: 1 }}>
        <Box>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell style={{ background: "#EBEEFE" }} align="center">
                  Team Name
                </TableCell>
                <TableCell style={{ background: "#EBEEFE" }} align="center">
                  Matches
                </TableCell>
                <TableCell style={{ background: "#EBEEFE" }} align="center">
                  Total wins
                </TableCell>
                <TableCell style={{ background: "#EBEEFE" }} align="center">
                  Point
                </TableCell>
                <TableCell style={{ background: "#EBEEFE" }} align="center">
                  Tournaments
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {listItems.length <= 0 ? (
                <div style={{ minHeight: 100 }}>No data found</div>
              ) : (
                listItems

                  .sort((a, b) => b?.point - a?.point)
                  ?.map((order) => {
                    return (
                      <TableRow hover key={order?.id}>
                        <TableCell sx={{ maxWidth: "200px", wordBreak: "break-all" }}>
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
      <Divider />
    </Card>

    
  );
};

export default Homepage;
