import * as React from 'react';
import Button from '@mui/material/Button';
import { styled } from '@mui/material/styles';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import { Grid } from '@mui/material';
import { TextValidator, ValidatorForm } from 'react-material-ui-form-validator';

const BootstrapDialog = styled(Dialog)(({ theme }) => ({
    '& .MuiDialogContent-root': {
        padding: theme.spacing(2),
    },
    '& .MuiDialogActions-root': {
        padding: theme.spacing(1),
    },
}));

export default function ManageAccountDialog(props) {
    let {
        open,
        handleClose,
        item
    } = props;

    const handleSubmit = async () => {

    }

    return (
        <BootstrapDialog
            maxWidth="lg"
            minWidth="lg"
            width="lg"
            onClose={handleClose}
            fullWidth
            open={open}
        >
            <ValidatorForm onSubmit={handleSubmit}>
                <DialogTitle sx={{ m: 0, p: 2 }} id="customized-dialog-title">
                    Thêm mới/Cập nhật cầu thủ
                </DialogTitle>
                <DialogContent dividers>
                    <Grid container>
                        <Grid item>
                            <TextValidator
                                label={'sssss'}
                                type="text"
                                name="name"
                                validators={['required']}
                                errorMessages={['This field is required']}
                            />
                        </Grid>
                    </Grid>
                </DialogContent>
                <DialogActions>
                    <Button variant="contained" onClick={handleClose} color="error">
                        Hủy
                    </Button>
                    <Button variant="contained" type="submit">
                        Lưu
                    </Button>
                </DialogActions>
            </ValidatorForm>
        </BootstrapDialog>
    );
}