package com.example.footballmanager.service.impl;

import com.example.footballmanager.dao.PlayerDao;
import com.example.footballmanager.dao.TeamDao;
import com.example.footballmanager.exception.TransferException;
import com.example.footballmanager.model.Player;
import com.example.footballmanager.model.Team;
import com.example.footballmanager.service.PlayerService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private static final int MONTH_TRANSFER_VALUE = 100000;
    private final PlayerDao playerDao;
    private final TeamDao teamDao;

    @Override
    public Player add(Player player) {
        return playerDao.add(player);
    }

    @Override
    public Player get(Long id) {
        return playerDao.get(id).orElseThrow(
                () -> new NoSuchElementException("No player found by id: " + id));
    }

    @Override
    public List<Player> getAll() {
        return playerDao.getAll();
    }

    @Override
    public List<Player> getAllByTeam(Long teamId) {
        return playerDao.getAllByTeam(teamId);
    }

    @Override
    public Player update(Player player) {
        return playerDao.update(player);
    }

    @Override
    public void delete(Long id) {
        playerDao.delete(id);
    }

    @Override
    public Player transferToAnotherTeam(Long id, Long teamId) {
        Player player = playerDao.get(id).orElseThrow(
                () -> new NoSuchElementException("No player found by id: " + id));
        if (Objects.equals(player.getTeam().getId(), teamId)) {
            throw new TransferException("It is not possible to transfer a player"
                    + " to the same team!");
        }
        Team newTeam = teamDao.get(teamId).orElseThrow(
                () -> new NoSuchElementException("No team found by id: " + teamId));
        BigDecimal transferCost = calcTransferCost(player);
        return makeTransfer(player, newTeam, transferCost);
    }

    private Player makeTransfer(
            Player player,
            Team newTeam,
            BigDecimal transferCost
    ) {
        Team oldTeam = player.getTeam();
        BigDecimal oldTeamBalance = oldTeam.getBalance();
        BigDecimal newTeamBalance = newTeam.getBalance();
        if (oldTeamBalance == null || newTeamBalance == null) {
            throw new TransferException("Old team and new team balances should be valid");
        }
        if (newTeamBalance.compareTo(transferCost) < 0) {
            throw new TransferException("Not enough money on the balance to make transfer!"
                    + " Team - '" + newTeam.getName()
                    + "', balance: " + newTeamBalance + ", but needs: "
                    + transferCost.setScale(2, RoundingMode.HALF_UP));
        }
        newTeam.setBalance(newTeamBalance.subtract(transferCost));
        oldTeam.setBalance(oldTeamBalance.add(transferCost));
        player.setTeam(newTeam);
        return playerDao.transferToAnotherTeam(player, oldTeam, newTeam);
    }

    private BigDecimal calcTransferCost(Player player) {
        int careerMonths = (int) ChronoUnit.MONTHS.between(
                player.getCareerStartDate(), LocalDate.now());
        if (careerMonths < 0) {
            throw new TransferException("Invalid player career start date: "
                    + player.getCareerStartDate());
        }
        BigDecimal playerCost = BigDecimal.valueOf(careerMonths)
                .multiply(BigDecimal.valueOf(MONTH_TRANSFER_VALUE))
                .divide(BigDecimal.valueOf(player.getAge()), RoundingMode.HALF_UP);
        BigDecimal commission = playerCost
                .multiply(BigDecimal.valueOf(player.getTeam().getFee()))
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
        return playerCost.add(commission);
    }
}
